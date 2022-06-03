<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message</title>
<style>
	#show{
		width: 500px;
		height: 300px;
		border: 1px solid blue;
	}
</style>
</head>
<body>
  <% 
  	String user = request.getParameter("user");
  %>
  <div id="show">
  	<div class="row">user1> test</div>
  </div>
  <input type="text" name="content">
  <input type="hidden" name="writer" value="<%=user%>">
  
  <script>
		  document.querySelector('input[name="content"]').addEventListener('change', changeCall);
		  function changeCall(e) {
		    let content = document.querySelector('input[name="content"]').value;
		    let writer = document.querySelector('input[name="writer"]').value;
		   
		  	fetch('message', {
		  		method: 'POST',
		  		headers: {'Content-type': 'application/x-www-form-urlencoded'},
		  		body: 'writer=' + writer + '&content=' + content
		  		})
		  		.then(result => {
		  			console.log(result);
		  			e.target.value = ''; //e.target => input
		  		})
		  		.catch(err => console.log(err));
		  }
	  
	  let lastMsg = -1;
	  setInterval(e=>{
	    //ajax호출(서버로부터 message서블릿 호출해서 처리)
	    fetch('message')
	      .then(result => result.json()) //가져온 결과값을 String->json
	      .then(resolve=> {
	    	  //기존 화면의 메세지 지우기
	    	  let divs = document.querySelectorAll('.row');
	    	  divs.forEach(elem => elem.remove()); //지우기
	    	  
	    	  fitAry = resolve.filter(elem => {
	    		  return elem.msgId > lastMsg - 14;
	    	  })
	    	  
	    	  //조회된 메시지를 새로 그려주기
	    	  let show = document.getElementById('show');
	    	  resolve.forEach(row => {
	    		lastMsg = row.msgId;
	    	  	let div = createRow(row);
	    	  	show.append(div);
	    	  });
	      })
	      .catch(error => console.log(error));//에러 발생하면 출력
	  }, 1500); //1.5초마다 리프래쉬
	  
	  
	  function createRow(message){
		  let div = document.createElement('div');
		  div.setAttribute('class', 'row'); //지울 용도로 클래스 선언
		  let txt = document.createTextNode(message.writer + '>' + message.content)
		  div.append(txt);
		  return div;
	  }
	</script>
</body>
</html>