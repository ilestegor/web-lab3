const xIndex = 0;
const yIndex = 1;
const rIndex = 2;
const regEx = /-?\d+/g;
let submitBtn = document.querySelector(".submit-btn");
let resetBtn = document.querySelector(".reset-btn");
//reset values when reloading
if (document.readyState === 'loading') {
    document.getElementById("input-form:x").value = "";
    getInput()[yIndex].value = "";
}
submitBtn.addEventListener('click', (e) => {
    if (validateInput(e)) {
        let x = document.getElementById("input-form:x").value;
        let y = getInput()[yIndex].value;
        let r = document.querySelector("input[type=radio]:checked")
        addDotsForForm(x, y);
        drawXY(parseInt(x), parseInt(y), r.value)
    }
})
resetBtn.addEventListener('click', (e) => resetInput(e))

//get data from user
function getInput() {
    let x = document.querySelector(".x-value").textContent.trim().match(regEx);
    let y = document.getElementById("input-form:y-input");
    let r = document.querySelector("input[type=radio]:checked");
    return [x, y, r];
}

//get fields for error messages
function getErrorTextField() {
    let xError = document.getElementById("input-form:x-error-field");
    let yError = document.getElementById("input-form:y-error-field");
    let rError = document.getElementById("input-form:r-error-field");
    return [xError, yError, rError];
}

function validateInput(e) {
    //receiving data from user
    e.preventDefault();

    let xElement = document.querySelector(".ui-slider");
    let yElement = getInput()[yIndex];
    let rElement = document.querySelectorAll(".x-button")
    let x = getInput()[xIndex]
    let y = getInput()[yIndex].value.trim().replace(",", ".");
    let r = getInput()[rIndex];


    let xFlag = false;
    let yFlag = false;
    let rFlag = validateRInput(r, rElement);

    if (isNumber(x) && checkX(x)) {
        xFlag = true;
    }


    if (isNumber(y)) {
        if (checkY(y)) {
            yElement.setCustomValidity("");
            yFlag = true;
        } else {
            yElement.setCustomValidity(" ")
        }
    } else {
        yElement.setCustomValidity(" ")

    }
    return xFlag && yFlag && rFlag;
}

function validateRInput(r, rElement) {
    let rFlag = false;
    if (checkR(r)) {
        rElement.forEach((el) => el.setCustomValidity(""))
        rFlag = true;
    } else {
        rElement.forEach((el) => el.setCustomValidity(" "))
    }
    return rFlag;
}


//function for resetting input;
function resetInput(event) {
    event.preventDefault();
    let form = document.getElementById("input-form");

    let xError = getErrorTextField()[xIndex];
    let yError = getErrorTextField()[yIndex];
    let rError = getErrorTextField()[rIndex];


    let xField = document.getElementById("input-form:j_idt28");
    let yField = getInput()[yIndex];
    let rField = document.querySelectorAll("input[type=radio]");
    xError.innerText = "";
    yError.innerText = "";
    rError.innerText = "";
    localStorage.clear()
    form.reset();

    calculator.setState(initState);
}


//helper functions for main validation function
function isNumber(value) {
    return value != null && value !== "" && !isNaN(Number(value));
}

function checkX(value) {
    return value != null;
}

function checkXArea(value) {
    return value >= -5 && value <= 5;

}

function checkY(value) {
    return value >= -3 && value <= 3;
}

function checkR(value) {
    return value !== null;
}