let elt = document.getElementById('calculator');
let rInput = document.querySelectorAll(".input-wrapper td:nth-of-type(n) input[type=radio]");
let rError = getErrorTextField()[rIndex];
let rField = document.querySelectorAll("input[type=radio]");
let rChecked = document.querySelectorAll(".x-button")


for (let i = 0; i <rInput.length; i++) {
    rInput[i].onchange = () => {
        drawGraphByR(rInput[i].value)
    }}


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
    yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE
});
calculator.setMathBounds({
    left: -4,
    right: 4,
    bottom: -4,
    top: 4
});
let initState= calculator.getState();




//draw graph by r input point
function drawGraphByR(r){
    if (validateRInput(r, rError, rField, rChecked))
        calculator.setExpression({
            id: 'area-1',
            latex: 'y\\le'+  r  + '\\ \\left\\{0\\le x\\le\\frac{' + r + '}{2}\\right\\}\\left\\{y\\ge0\\right\\}'
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
//
//
//
// //check if r is specified, if true -> allow to get coord by graph click, else show pop up with text
// function handleGraphClick(e){
//     let r = rInput.value;
//     if (validateAndSetValidationText(checkR,r, getErrorTextField()[rIndex], getInput()[rIndex], "", "Введите число от 2...5", "Введите число"))
//     {
//         let calculatorRect = elt.getBoundingClientRect();
//         const x = e.clientX - calculatorRect.left;
//         const y = e.clientY - calculatorRect.top;
//         const mathCoordinates = calculator.pixelsToMath({x: x, y: y});
//         post_req(`http://localhost:8080/lab2-1.0-SNAPSHOT/controller`, e, `x=${mathCoordinates.x}&y=${mathCoordinates.y}&r=${r}&function=check`)
//     }else {
//         popUp("Укажите значение R, чтобы тыкнуть </br> на график и отправить данные");
//         elt.removeEventListener('click', handleGraphClick);
//     }
// }
//
// //accepts x, y points to draw a dot on graph
// function drawXY(x, y){
//     calculator.setExpression({
//         id: x + '' + y,
//         latex: '\\left(' + x + ',' + y + '\\right)',
//     })
// }


