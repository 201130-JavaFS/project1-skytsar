const url = 'http://localhost:8080/project-1/';



document.getElementById("loginbtn").addEventListener('click', loginFunc);
document.getElementById("requestAdd").addEventListener('click', showreqform);
document.getElementById("submitRequest").addEventListener('click', addRequest);

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
    document.getElementById("useroptions").style.display = "block";
    
  }else{
    document.getElementById('login-row').innerText="Login failed! Reload the page of the computer will explode!"; 
  }

}

async function makeTable(){
	
	let response = await fetch(url+"getRequests", {credentials: 'include'});
	if(response.status===200){
      console.log(response);
      let data = await response.json();
      for(let req of data){
	  	let cell = document.createElement("button");
      	cell.innerHTML = req.id;
      	row.appendChild(cell);

      	let cell2 = document.createElement("td");
      	cell2.innerHTML = req.ammount;
      	row.appendChild(cell2);

      	let cell3 = document.createElement("td");
      	cell3.innerHTML = req.submitTime;
      	row.appendChild(cell3);

      	let cell4 = document.createElement("td");
      	cell4.innerHTML = req.resolveTime;
      	row.appendChild(cell4);

      	let cell5 = document.createElement("td");
      	cell5.innerHTML = req.resolverID;
      	row.appendChild(cell5);

      	let cell6 = document.createElement("td");
      	cell6.innerHTML = req.statusID;
      	row.appendChild(cell6);

      	
        let cell7 = document.createElement("td");
        cell7.innerHTML = req.typeID;
        row.appendChild(cell7);
      	

	  	document.getElementById("request table").appendChild(row);
	  }
	}

}
function showreqform(){
	document.getElementById("requesttemplate").style.display = "block";
}


async function addRequest(){
	let amnt = document.getElementById("amount").value;
	let desc= document.getElementById("description").value;
	let typ= document.getElementById("typeID").value;
	
  let basereq = {
    amount:amnt,
    description:desc,
    type:typ
  };
	let resp = await fetch(url+'addRequest', {
    method:"POST",
    body: JSON.stringify(basereq),
    credentials: 'include'
  });
	if(resp.status===200){
      document.getElementById('requesttemplate').innerText="Request submitted"
    
  }else{
  	document.getElementById('requesttemplate').innerText="Request failed"
    
  }
	
}

