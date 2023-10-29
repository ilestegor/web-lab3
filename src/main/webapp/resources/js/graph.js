let elt = document.getElementById('calculator');
let rInput = document.querySelectorAll(".input-wrapper td:nth-of-type(n) input[type=radio]");
const redColor = "#00fff7";
const greenColor = "#aa00ff";
const cordsPattern = /(-?\d+\.\d+)(-?\d+\.\d+)/;
let dots = [];
//graph config
let calculator = Desmos.GraphingCalculator(elt, {
    keypad: false,
    expressions: false,
    settingsMenu: false,
    invertedColors: true,
    xAxisLabel: 'x',
    yAxisLabel: 'y',
    xAxisStep: 1,
    yAxisStep: 1,
    lockViewport: true,
    xAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
    yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
});
calculator.setMathBounds({
    left: -5,
    right: 5,
    bottom: -5,
    top: 5
});
let initState = calculator.getState();

//handles click on graph, check area, draw dots and send request
elt.addEventListener('click', (e) => {
    handleGraphClick(e);
})

//change graph by R
for (let i = 0; i < rInput.length; i++) {
    rInput[i].onchange = () => {
        drawGraphByR(rInput[i].value)
    }
}


//observer for changing dots colors by changing r value
calculator.observeEvent('change', () => {
    let rValue = document.querySelector("input[type=radio]:checked");
    dots.forEach((x) => {
        let nums = x.match(cordsPattern);
        if (rValue != null && nums != null) {
            drawXY(parseFloat(nums[1]), parseFloat(nums[2]), parseFloat(rValue.value))
        }
    })
})


//draw graph by r input point
function drawGraphByR(r) {
    calculator.setExpression({
        id: 'area-1',
        latex: 'y\\le' + r + '\\ \\left\\{0\\le x\\le\\frac{' + r + '}{2}\\right\\}\\left\\{y\\ge0\\right\\}'
    });
    calculator.setExpression({
        id: 'area-2',
        latex: 'x^{2}+y^{2}\\le\\frac{' + r + '}{2}^{2}\\left\\{x<0\\right\\}\\left\\{y>0\\right\\}'
    });
    calculator.setExpression({
        id: 'area-3',
        latex: 'y\\ >=\\ - ' + r + '-x * 2\\left\\{x<0\\right\\}\\left\\{y<0\\right\\}'
    })
}

//check if r is specified, if true -> allow to get coord by graph click, else show pop up with text
function handleGraphClick(e) {
    e.preventDefault();
    let calculatorRect = elt.getBoundingClientRect();
    const x = e.clientX - calculatorRect.left;
    const y = e.clientY - calculatorRect.top;
    const mathCoordinates = calculator.pixelsToMath({x: x, y: y});
    const rValue = document.getElementById("graphSelect:graph-r");

    document.getElementById("graphSelect:graph-x").value = mathCoordinates.x;
    document.getElementById("graphSelect:graph-y").value = mathCoordinates.y;
    rValue.value = document.querySelector("input[type=radio]:checked");
    if (document.querySelector("input[type=radio]:checked") !== null) {
        rValue.value = document.querySelector("input[type=radio]:checked").value;
        if (checkY(mathCoordinates.y) && checkXArea(mathCoordinates.x)) {
            addDots(mathCoordinates.x, mathCoordinates.y)
        }
        drawXY(parseFloat(mathCoordinates.x), parseFloat(mathCoordinates.y), parseFloat(rValue.value))
    }
    updateBeanValues();
}

//accepts x, y points to draw a dot on graph
function drawXY(x, y, r) {
    if (checkArea(x, y, r)) {
        drawDots(x, y, greenColor)
    } else {
        drawDots(x, y, redColor)
    }
}

//adds dots to localStorage
function addDots(x, y) {
    dots.push(x + '' + y);
}

function addDotsForForm(x, y) {
    dots.push(`${x}.0${y}.0`)
}

function drawDots(x, y, color) {
    calculator.setExpression({
        id: x + '' + y,
        color: color,
        latex: '\\left(' + x + ',' + y + '\\right)',
    })
}




