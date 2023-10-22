const xIndex = 0;
const yIndex = 1;
const rIndex = 2;
const regEx = /-?\d+/g;

//get data from user
function getInput(){
    let x = document.querySelector(".x-value").textContent.trim().match(regEx);
    let y = document.getElementById("input-form:y-input");
    let r = document.querySelector("input[type=radio]:checked");
    // console.log([x.toString(), y.value, r.value])
    return [x, y, r];
}

//get fields for error messages
function getErrorTextField(){
    let xError = document.getElementById("x-error-field");
    let yError = document.getElementById("y-error-field");
    let rError = document.getElementById("r-error-field");
    return [xError, yError, rError];
}

function validateInput(event){
    event.preventDefault();
    //receiving data from user
    let x = getInput()[xIndex].toString();
    let y = getInput()[yIndex].value.trim().replace(",", ".");
    let r = getInput()[rIndex].value.trim().replace(",", ".");

    //get elements for error text
    let xError = getErrorTextField()[xIndex];
    let yError = getErrorTextField()[yIndex];
    let rError = getErrorTextField()[rIndex];

    //get input fields to give them error styles
    let xField = document.getElementById("input-form:j_idt28");
    let yField = getInput()[yIndex];
    let rField = document.querySelectorAll("input[type=radio]");

    let xFlag = false;
    let yFlag = false;
    let rFlag = false;

    if (isNumber(x)){
        if (checkX(x)){
            setSingleSuccess(xError, xField, "", ["error-slider"])
            xFlag = true;
        } else {
            setSingleError(xError, xField, "Choose number from the right range", ["error-slider"])
        }
    } else {
        setSingleError(xError, xField, "Choose number", ["error-slider"])
    }

    if (isNumber(y)){
        if (checkY(y)){
            setSingleSuccess(yError, yField, "", ["error-text-input"]);
            yFlag = true;
        } else {
            setSingleError(yError, yField, " Choose number from -3...3", ["error-text-input"])
        }
    } else {
        setSingleError(yError, yField, "Type a number", ["error-text-input"])
    }

   if (isNumber(r)){
       if (checkR(r)){
           setMultipleSuccess(rError, rField, "", ["error-text-input"])
           rFlag = true;
       } else {
           setMultipleError(rError, rField, "Out of range", ["error-text-input"])
       }
   } else {
       setMultipleError(rError, rField, "Choose a number", ["error-text-input"])
   }

    return xFlag && yFlag && rFlag;
}




//function for resetting input;
function resetInput(event){
    let form  = document.getElementById("input-form");

    let xError = getErrorTextField()[xIndex];
    let yError = getErrorTextField()[yIndex];
    let rError = getErrorTextField()[rIndex];

    //get input fields to give them error styles
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

function checkX(value){
    return value !== null && value >= -5 && value <= 5;
}
function checkY(value){
    return value >= -3 && value <= 3;
}
function checkR(value){
    return value >= 1 && value <= 3;
}