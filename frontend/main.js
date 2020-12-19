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

function makeTable(){
	  let row = document.createElement("tr");
      let cell = document.createElement("th");
      cell.innerHTML = "Request ID";
      row.appendChild(cell);
      let cell2 = document.createElement("th");
      cell2.innerHTML = "Ammount";
      row.appendChild(cell2);
      let cell3 = document.createElement("th");
      cell3.innerHTML = "Time submitted";
      row.appendChild(cell3);
      let cell4 = document.createElement("th");
      cell4.innerHTML = "Time resolved";
      row.appendChild(cell4);
      let cell5 = document.createElement("th");
      cell5.innerHTML = "Requester ID";
      row.appendChild(cell5);
      let cell6 = document.createElement("th");
      cell6.innerHTML = "Status";
      row.appendChild(cell6);
      let cell7 = document.createElement("th");
      cell7.innerHTML = "Type";
      row.appendChild(cell7);

	  document.getElementById("request table").appendChild(row);

}

function fillTable(){




}

function getuser(){
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

