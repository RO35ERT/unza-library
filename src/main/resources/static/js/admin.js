const approve = document.querySelectorAll(".approve");
const confirm = document.querySelector(".confirm");

const confirm_text = document.querySelector(".confirm_text");
approve.forEach((approveBtn)=>{
    approveBtn.addEventListener('click',(e)=>{
        e.preventDefault()
        const parent = approveBtn.parentNode.parentNode;
        confirm_text.innerHTML = parent.children[2].innerHTML;
        confirm.style.display="block";
    })
});

const close = document.querySelector(".close");
const no = document.querySelector('.no');
close.addEventListener('click',(e)=>{
    confirm.style.display="none";
})

no.addEventListener('click',(e)=>{
    confirm.style.display="none";
})

const yes = document.querySelector(".yes");

yes.addEventListener('click',(e)=>{
    fetch(`http://localhost:8080/admin/approveReservation/${confirm_text.innerHTML}`).then(r => r);
    confirm.style.display="none";
    location.reload();
})