let days = {
    0: "Sunday",
    1: "Monday",
    2: "Tuesday",
    3: "Wednesday",
    4: "Thursday",
    5: "Friday",
    6: "Saturday"
}
const options = {
    month: "long"
}
window.onload = () => {
    time();
    amPm();
    whatDay()
    setInterval(() => {
        time()
        amPm();
    }, 12000)
}

function time(){
    //create Date object
    let date = new Date()
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();

    //add zero to start of the number
    hours = changeToTwelveHourClock(addZero(hours));
    minutes = addZero(minutes);
    seconds = addZero(seconds);

    //change html
    document.getElementsByClassName('hours')[0].innerHTML = hours;
    document.getElementsByClassName('minutes')[0].innerHTML = minutes;
    document.getElementsByClassName('seconds')[0].innerHTML = seconds;
}



function changeToTwelveHourClock(hours){
    //change to 12-hour format
    return (hours > 12) ? (hours - 12) : hours;

}
function addZero(value){
    return (value <= 9) ? ("0" + value) : value;
}
function amPm(){
    let date = new Date();
    let hours = date.getHours();
    let amPm = document.querySelector(".day-type");

    (hours >= 12) ? amPm.innerText = "PM" : amPm.innerText = "AM";
}
function whatDay(){
    let date = new Date();
    let day = date.getDay();
    let dayNumber = date.getDate();
    let month = new Intl.DateTimeFormat("en-US", options).format(new Date());
    document.querySelector(".day-of-the-week").innerHTML = month + ", " + dayNumber +  " " +  days[day]

}