/**
 * 
 */


function selectfold(val,len,name)
{
	var txtnew='txtfold'+val;
	 document.getElementById(txtnew).style.backgroundColor = "#87cefa";
	 for(var i=0;i<len;i++)
	{
		 
		 if(i!=val)
			 {
			 txt1='txtfold'+i;
			 document.getElementById(txt1).style.backgroundColor = "";
			 }
		 else
			 {
			 txt1='txtfold'+i;
			 document.getElementById(txt1).style.backgroundColor = "#87cefa";
			 }
	}
	 sessionStorage.selctedfold=name;

		 
}

function defaultfold()
{
	
	 var x = document.querySelectorAll("[id^=txtfold]");
	 var i;
	    for (i = 0; i < x.length; i++) {
	    
	        x[i].style.backgroundColor = "";
	    }
}

function defaultfile()
{
	
	 var x = document.querySelectorAll("[id^=txtfile]");
	 var i;
	    for (i = 0; i < x.length; i++) {
	    	x[i].style.backgroundColor = "";
	    }
}

function selectfile(val,len,name)
{
	
	 var txtnew='txtfile'+val;
	 document.getElementById(txtnew).style.backgroundColor = "#87cefa";
	 for(var i=0;i<len;i++)
		{
			 
			 if(i!=val)
				 {
				 txt1='txtfile'+i;
				 document.getElementById(txt1).style.backgroundColor = "";
				 }
			 else
				 {
				 txt1='txtfile'+i;
				 document.getElementById(txt1).style.backgroundColor = "#87cefa";
				 }
		}
	 sessionStorage.selctedfold=name;

}


function showhidemsg()
{

		var val= document.getElementById("foldmsg").value;
		if(val!=null && val!='')
			{
			document.getElementById("createTable").style.display="";
			
			}
		else
			{
			document.getElementById("createTable").style.display="none";
			}
		
		var val1= document.getElementById("uploadmsg").value;
		if(val!=null && val!='')
			{
			document.getElementById("uploadTable").style.display="";
			
			}
		else
			{
			document.getElementById("uploadTable").style.display="none";
			}
		 
}
 
function showHideUploadTable()
{
   if (document.getElementById("uploadTable").style.display == "none" ) {
       document.getElementById("uploadTable").style.display="";

   } else {
      document.getElementById("uploadTable").style.display="none";

}
}

  



function filefunction(selectedfile)
{
	  var selectfile = selectedfile;
	   document.getElementById("myFile").value=selectfile;
	  var form=document.getElementById("UserFile");
	  form.submit();
	  
		  
}

function folderfunction(selectedfile1)
{
	
	 var selectfile = selectedfile1;
	 document.getElementById("myFolder").value=selectfile;
	 var form=document.getElementById("UserForm");
	 form.submit();
 
		  
}

function delFunction()
{

	document.getElementById("myFold").value=sessionStorage.selctedfold;
	sessionStorage.clear();
	document.getElementById("UserOpt").action="delete";
	document.getElementById("UserOpt").submit();
		 	
}

function createFunction()
{
	 document.getElementById("UserOpt").action="createfolder";
	 	document.getElementById("UserOpt").submit();
}

function uploadFunction()
{
	 document.getElementById("UserOpt").action="upload";
	 	document.getElementById("UserOpt").submit();
}

function getfilevalue()
{
	if(document.getElementById("file")==null ||document.getElementById("file")=='')
		{
		alert('Please select the File');
		var but=document.getElementById("uploadbutton");
		but.click();
		}
	else
		{
	document.getElementById("upload").submit();
		}
	
}
