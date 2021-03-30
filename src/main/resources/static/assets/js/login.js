function selectIdentity(o)
{
	if(o==0)
	{
		document.getElementById("myForm").action="/user/login";
	}
	else if(o==1)
	{
		document.getElementById("myForm").action="/technician/login";
	}else if(o==2){
        document.getElementById("myForm").action="/admin/login";
	}
}

function login()
{
  var url = document.getElementById("myForm").action;
  console.log(url);
  var name=document.getElementById("form-username").value;
  var password=document.getElementById("form-password").value;

  alert(1);

  $.post({
      url: url,
      data: {
          'name':name,
          'password':password
      },
      success: function (data) {
        alert(data);
      }
  })
}
