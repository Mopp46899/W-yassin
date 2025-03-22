// قائمة الأسئلة العميقة
const questions = [
    "هل تؤمن بأن حياتك تملك هدفًا محددًا تسعى لتحقيقه؟",
    "هل تعتقد أن الأشخاص في حياتك يتفاعلون معك بسبب حبهم الحقيقي لك؟",
    "هل تجد صعوبة في الثقة بالآخرين؟",
    "هل تعتقد أن الشخص الذي لا يواجه تحديات في حياته يعيش حياة كاملة؟",
    "هل ترى أن السعادة تأتي من الإنجاز أم من العلاقات الإنسانية؟",
    "هل تجد أن المنافسة الصحية تساهم في تحفيزك لتحقيق الأفضل؟",
    "هل تؤمن بأن الفشل هو خطوة نحو النجاح؟",
    "هل تفضل العيش بحياة مستقرة أم مغامرة مليئة بالتحديات؟",
    "هل تسعى دائمًا لتحسين نفسك وتحقيق أهدفك الشخصية؟",
    "هل تفضل أن يكون لديك مجموعة صغيرة من الأصدقاء المقربين بدلًا من الكثير من المعارف؟"
];

let currentQuestionIndex = 0;
let answers = [];

document.getElementById('start-quiz').addEventListener('click', startQuiz);
document.getElementById('retake-btn').addEventListener('click', restartQuiz);

// بدء الاختبار
function startQuiz() {
    document.getElementById('start-quiz').style.display = 'none';
    document.getElementById('question-container').style.display = 'block';
    loadQuestion();
}

// تحميل السؤال العشوائي
function loadQuestion() {
    if (currentQuestionIndex < 10) {
        // اختيار سؤال عشوائي
        const randomIndex = Math.floor(Math.random() * questions.length);
        const question = questions[randomIndex];
        document.getElementById('question').textContent = question;
    } else {
        calculatePersonality();
    }
}

// التعامل مع الأزرار
const answerBtns = document.querySelectorAll('.answer-btn');
answerBtns.forEach(button => {
    button.addEventListener('click', function () {
        answers.push(this.getAttribute('data-answer'));
        currentQuestionIndex++;
        loadQuestion();
    });
});

// حساب النتيجة بناءً على الإجابات
function calculatePersonality() {
    let score = {
        "محارب": 0,
        "فارس": 0,
        "أمير": 0,
        "الجشع": 0,
        "عادي": 0
    };

    answers.forEach((answer, index) => {
        if (answer === "yes") {
            if (index <= 2) {
                score["محارب"]++;
            } else if (index <= 4) {
                score["فارس"]++;
            } else if (index <= 6) {
                score["أمير"]++;
            } else if (index <= 8) {
                score["الجشع"]++;
            } else {
                score["عادي"]++;
            }
        }
    });

    let personality = getMaxPersonality(score);
    showResult(personality);
}

// تحديد الشخصية بناءً على النتيجة
function getMaxPersonality(score) {
    let maxScore = Math.max(...Object.values(score));
    for (let personality in score) {
        if (score[personality] === maxScore) {
            return personality;
        }
    }
}

// عرض النتيجة
function showResult(personality) {
    document.getElementById('question-container').style.display = 'none';
    document.getElementById('result').style.display = 'block';
    document.getElementById('personality').textContent = `شخصيتك هي: ${personality}`;
}

// إعادة الاختبار
function restartQuiz() {
    currentQuestionIndex = 0;
    answers = [];
    document.getElementById('result').style.display = 'none';
    document.getElementById('start-quiz').style.display = 'block';
}