
let oldRadioElem = document.querySelectorAll(".input-wrapper td:nth-of-type(n)")
for (let oldElem of oldRadioElem) {
    let tdElement = document.querySelector(".input-wrapper td:nth-of-type(n)")
    tdElement.remove();
}

let placeToAdd = document.querySelector(".input-wrapper tr:nth-of-type(n)")
for (let i = oldRadioElem.length - 1; i >= 0; i--) {
    oldRadioElem[i].querySelector("input[type=radio]").classList.add("x-button")
    oldRadioElem[i].querySelector("input[type=radio]").removeAttribute('checked');
    oldRadioElem[i].querySelector("label").remove()
    let count = oldRadioElem[i].querySelector("input[type=radio]").value
    placeToAdd.insertAdjacentHTML("afterbegin", `<td><span>${count}</span><label>${oldRadioElem[i].innerHTML}<span class="custom-x-button"></span></label></td>`)
}
