function btnClick() {
  const mydiv = document.getElementById('my-div');

  if(mydiv.style.visibility === 'hidden') {
    mydiv.style.visibility = 'visible';
  }else {
    mydiv.style.visibility = 'hidden';
  }
}