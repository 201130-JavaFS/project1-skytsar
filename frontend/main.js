const url = 'http://localhost:8080/project-1/';



document.getElementById("loginbtn").addEventListener('click', loginFunc);

async function loginFunc() {
  
  let usern = document.getElementById("username").value;
  let userp = document.getElementById("password").value;
  

  let user = {
    username:usern,
    password:userp
  };

  let resp = await fetch(url+'login', {
    method:"POST",
    body: JSON.stringify(user),
    credentials: 'include'
    //Credentials:include will ensure that they cookie is captured, future fetch requests
    //will also require this value in order to send the cookie back. 
  });

  if(resp.status===200){
    document.getElementById('login-row').innerText="YOU HAVE LOGGED IN";  
  }else{
    document.getElementById('login-row').innerText="Login failed! Reload the page of the computer will explode!"; 
  }

}
