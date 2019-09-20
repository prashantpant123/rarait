

function printStaffDetails()  {
  var x = document.getElementById("print");
  var y= document.getElementById("profile");
  var z= document.getElementById("button");
  if (x.style.display = "none") {
    x.style.display = "block";
    y.style.display="none";
    z.style.display="none";
    window.print();
    y.style.display="block";
    x.style.display = "none";
    z.style.display="block";
  }
}
function printStudentDetails() {
    var x = document.getElementById("print");
    var y= document.getElementById("profile");
    var z= document.getElementById("button");
    if (x.style.display = "none") {
      x.style.display = "block";
      y.style.display="none";
      z.style.display="none";
      window.print();
      y.style.display="block";
      x.style.display = "none";
      z.style.display="block";
    }
  }

