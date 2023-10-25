const xIndex = 0;
const yIndex = 1;
const rIndex = 2;
const regEx = /-?\d+/g;
let submitBtn = document.querySelector(".submit-btn");
let resetBtn = document.querySelector(".reset-btn");


submitBtn.addEventListener('click', (e) => {
    validateInput(e);
})
resetBtn.addEventListener('click', (e) => resetInput(e))

//get data from user
function getInput(){
    let x = document.querySelector(".x-value").textContent.trim().match(regEx);
    let y = document.getElementById("input-form:y-input");
    let r = document.querySelector("input[type=radio]:checked");
    return [x, y, r];
}

//get fields for error messages
function getErrorTextField(){
    let xError = document.getElementById("x-error-field");
    let yError = document.getElementById("y-error-field");
    let rError = document.getElementById("r-error-field");
    return [xError, yError, rError];
}

function validateInput(e){
    //receiving data from user
    e.preventDefault();

    let yElement = getInput()[yIndex];
    let rElement = document.querySelectorAll(".x-button")
    let y = getInput()[yIndex].value.trim().replace(",", ".");
    let r = getInput()[rIndex];

    //get elements for error text
    let yError = getErrorTextField()[yIndex];
    let rError = getErrorTextField()[rIndex];

    //get input fields to give them error styles
    let yField = getInput()[yIndex];
    let rField = document.querySelectorAll("input[type=radio]");

    let xFlag = true;
    let yFlag = false;
    let rFlag = validateRInput(r, rError, rField, rElement);

    if (isNumber(y)){
        if (checkY(y)){
            yElement.setCustomValidity("");
            setSingleSuccess(yError, yField, "", ["error-text-input"]);
            yFlag = true;
        } else {
            yElement.setCustomValidity(" ")
            setSingleError(yError, yField, " Choose number from -3...3", ["error-text-input"])

        }
    } else {
        yElement.setCustomValidity(" ")
        setSingleError(yError, yField, "Type a number", ["error-text-input"])
    }
   return xFlag && yFlag && rFlag;
}

function validateRInput(r, textError, errorFiled, rElement){
    let rFlag = false;
    if (checkR(r)){
        rElement.forEach((el) => el.setCustomValidity(""))
        setMultipleSuccess(textError, errorFiled, "", ["error-text-input"])
        rFlag = true;
    } else {
        rElement.forEach((el) => el.setCustomValidity(" "))
        setMultipleError(textError, errorFiled, "Choose a number", ["error-text-input"])
    }
    return rFlag;
}


//function for resetting input;
function resetInput(event){
    let form  = document.getElementById("input-form");

    let xError = getErrorTextField()[xIndex];
    let yError = getErrorTextField()[yIndex];
    let rError = getErrorTextField()[rIndex];


    let xField = document.getElementById("input-form:j_idt28");
    let yField = getInput()[yIndex];
    let rField = document.querySelectorAll("input[type=radio]");

    form.reset();
    setSingleSuccess(yError, yField, "", ["error-text-input"]);
    setMultipleSuccess(rError, rField, "", ["error-text-input"]);
    setMultipleSuccess(xError, xField, "", ["error-slider"]);

    calculator.setState(initState);
}
//function for setting error styles and error text
function setMultipleError(elem1, elem2, message, className){
    elem1.innerText = message;
    for (let i = 0; i < elem2.length; i++) {
        elem2[i].classList.add(className);
    }
}
function setSingleError(elem1, elem2, message, ...className){
    elem1.innerText = message;
    elem2.classList.add(className);
}
function setMultipleSuccess(elem1, elem2, message, ...className){
    elem1.innerText = message;
    for (let i = 0; i < elem2.length; i++) {
        elem2[i].classList.remove(className);
    }
}
function setSingleSuccess(elem1, elem2, message, ...className){
    elem1.innerText = message;
    elem2.classList.remove(className);
}


//helper functions for main validation function
function isNumber(value) {
    return value != null && value !== "" && !isNaN(Number(value));
}

function checkY(value){
    return value >= -3 && value <= 3;
}
function checkR(value){
    return value != null;
}