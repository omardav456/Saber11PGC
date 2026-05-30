
/* =========================
   API CONFIG (HTTPS REAL)
========================= */

const API = {
  EXAM: "https://exam-production-01a3.up.railway.app/api/saber11/simulacro",
  QUESTIONS: "https://saber11pgc-production.up.railway.app/api/saber11/question",
  NOTIFICATION: "https://notification-production-5b86.up.railway.app/api/saber11/notificationemail"
};

/* =========================
   AXIOS
========================= */

const http = axios.create({
  timeout: 10000,
});

/* =========================
   STATE
========================= */

let exam = null;
let questions = [];
let current = 0;
let score = 0;

/* =========================
   NAV
========================= */

function showView(id) {
  document.querySelectorAll(".view").forEach(v => v.classList.remove("active"));
  document.getElementById(id).classList.add("active");
}

/* =========================
   LOAD EXAMS
========================= */

async function loadExams() {
  try {
    const res = await http.get(`${API.EXAM}/getAll`);
    const data = res.data;

    const grid = document.getElementById("examGrid");
    grid.innerHTML = "";

    data.forEach(e => {
      grid.innerHTML += `
        <div class="card">
          <h3>${e.nombre || "Simulacro"}</h3>
          <p>Preguntas: ${e.questionsId?.length || 0}</p>
          <button onclick="startExam(${e.id})">Iniciar</button>
        </div>
      `;
    });

    showView("home");

  } catch (err) {
    console.error("Error exams", err);
  }
}

/* =========================
   LOAD EXAM FLOW
========================= */

async function startExam(id) {
  try {
    // 1. simulacro
    const examRes = await http.get(`${API.EXAM}/${id}`);
    exam = examRes.data;

    document.getElementById("examTitle").innerText =
      exam.nombre || "Simulacro";

    // 2. preguntas (SIN batch -> individual)
    questions = await loadQuestions(exam.questionsId);

    current = 0;
    score = 0;

    renderQuestion();
    showView("exam");

  } catch (err) {
    console.error("Error start exam", err);
  }
}

/* =========================
   LOAD QUESTIONS (PARALELO)
========================= */

async function loadQuestions(ids) {
  try {
    const promises = ids.map(id =>
      http.get(`${API.QUESTIONS}/${id}`).then(r => r.data)
    );

    return await Promise.all(promises);

  } catch (err) {
    console.error("Error questions", err);
    return [];
  }
}

/* =========================
   RENDER QUESTION
========================= */

function renderQuestion() {
  const q = questions[current];

  if (!q) return;

  document.getElementById("questionBox").innerHTML = `
    <h3>${q.question || q.text}</h3>

    ${(q.options || []).map(o => `
      <div class="option" onclick="selectAnswer('${o}')">
        ${o}
      </div>
    `).join("")}

    <p>Pregunta ${current + 1} de ${questions.length}</p>
  `;
}

/* =========================
   ANSWER LOGIC
========================= */

function selectAnswer(opt) {
  const q = questions[current];

  if (opt === q.answer) {
    score++;
  }

  current++;

  if (current < questions.length) {
    renderQuestion();
  } else {
    finishExam();
  }
}

/* =========================
   FINISH EXAM
========================= */

async function finishExam() {
  try {
    const payload = {
      examId: exam.id,
      score,
      total: questions.length
    };

    const res = await http.post(
      `${API.EXAM}/auto/`,
      payload
    );

    document.getElementById("resultBox").innerHTML = `
      <h2>Puntaje: ${score}/${questions.length}</h2>
      <p>Simulacro enviado correctamente</p>
    `;

    showView("result");

  } catch (err) {
    console.error("Error finish", err);
  }
}

/* =========================
   ADMIN (BÁSICO)
========================= */

async function loadAdmin() {
  try {
    const exams = await http.get(`${API.EXAM}/getAll`);
    const questions = await http.get(`${API.QUESTIONS}/getAll`);

    document.getElementById("totalExams").innerText = exams.data.length;
    document.getElementById("totalQuestions").innerText = questions.data.length;

    showView("admin");

  } catch (err) {
    console.error(err);
  }
}

/* INIT */
loadExams();