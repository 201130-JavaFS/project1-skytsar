const url = 'http://localhost:8080/project-1/';



document.getElementById("loginbtn").addEventListener('click', loginFunc);
document.getElementById("requestAdd").addEventListener('click', showreqform);
document.getElementById("submitRequest").addEventListener('click', addRequest);
document.getElementById("requestList").addEventListener('click', makeTable);
document.getElementById("change").addEventListener('click', changeRequest);

var loggedUser="";
var selectedRequest=0;
var statusFilter=-1;
var data="";

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
  	console.log(resp);
  	//loggedUser= await resp.json();
    document.getElementById('login-row').innerText="YOU HAVE LOGGED IN";  
    document.getElementById("useroptions").style.display = "block";
    
  }else{
    document.getElementById('login-row').innerText="Login failed! Reload the page of the computer will explode!"; 
  		}

}

async function makeTable(){
	document.getElementById("request select").style.display = "block";
	document.getElementById("table content").innerHTML="";
	statusFilter=document.getElementById("filterID").value;
	let response = await fetch(url+"getRequests", {credentials: 'include'});
	if(response.status===200){
      console.log(response);
      let data = await response.json();
      for(let req of data){
      	let row = document.createElement("tr");
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
      	cell5.innerHTML = req.authorID;
      	row.appendChild(cell5);

      	let cell6 = document.createElement("td");
      	cell6.innerHTML = req.resolverID;
      	row.appendChild(cell6);

      	let cell7 = document.createElement("td");
      	if(req.statusID==1){
      		cell7.innerHTML = "Pending";
      	}
      	else if(req.statusID==2){
      		cell7.innerHTML = "Accepted";
      	}
      	else if(req.statusID==3){
      		cell7.innerHTML = "Rejected";
      	}
      	row.appendChild(cell7);

      	
        let cell8 = document.createElement("td");
        if(req.typeID==1){
        	cell8.innerHTML = "LODGING";
        }
        else if(req.typeID==2){
        	cell8.innerHTML = "TRAVEL";
        }
        else if(req.typeID==3){
        	cell8.innerHTML = "FOOD";
        }
        else{
        	cell8.innerHTML = "OTHER";
        }
        row.appendChild(cell8);
      	
		if(statusFilter==0 || statusFilter==req.statusID){
	  	document.getElementById("table content").appendChild(row);
	  	}
	  }
	}

}
function showreqform(){
	document.getElementById("requesttemplate").style.display = "block";
}
async function changeRequest(){
	let reqID=document.getElementById("reqIDentry").value;
	let statID=document.getElementById("select action").value;
	let change = {
    requestID:reqID,
    statusID:statID
  };
	let respo = await fetch(url+'changeRequest', {
    method:"PUT",
    body: JSON.stringify(change),
    credentials: 'include'
  });
	if(respo.status===200){
      document.getElementById('request select').innerText="Request submitted"
    
  }else{
  	document.getElementById('request select').innerText="Request failed"
    
  }
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

