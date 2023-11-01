const reserve = document.querySelector('.reserve');
const cancel = document.querySelector(".cancel");
const confirm = document.querySelector(".confirm_reserve");
const confirmed = document.querySelector(".yes");
const unConfirm = document.querySelector(".no");

reserve.addEventListener('click',(e)=>{
    confirm.style.display = "block";
})

cancel.addEventListener('click',(e)=>{
    confirm.style.display = "none";
})

unConfirm.addEventListener('click',(e)=>{
    confirm.style.display = "none";
})

const create_reservation = document.querySelector('.create_reservation');

const cancel_ok = document.querySelector(".cancel_ok");

confirmed.addEventListener('click',(e)=>{
    create_reservation.style.visibility= "visible";
    confirm.style.display = "none";
})

cancel_ok.addEventListener('click',()=>{
    create_reservation.style.visibility= "hidden";
})