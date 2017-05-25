function readMultipleFiles(evt) {
    //Retrieve all the files from the FileList object
    var files = evt.target.files; 
    		
    if (files) {
        for (var i=0, f; f=files[i]; i++) {
	          var r = new FileReader();
            r.onload = (function(f) {
                return function(e) {
                    var contents = e.target.result;
                    
					var text = contents;
					
					text.replace("id_name", "Сергей");
					text.replace("id_surname", "Чепурнов");
					text.replace("id_middlename", "Анатольевич");
					
					document.getElementById("id_text").value = text;
					
					console.log(document.getElementById("id_text").value);
                };
            })(f);

            r.readAsText(f);
			
        }   
    } else {
	      alert("Failed to load files"); 
    }
  }

	document.getElementById('fileinput').addEventListener('change', readMultipleFiles, false);
  
	function saveTextAsFile() {
		var textToSave = document.getElementById("id_text").value;
		var textToSaveAsBlob = new Blob([textToSave]);
		var textToSaveAsURL = window.URL.createObjectURL(textToSaveAsBlob);
	 
		var downloadLink = document.createElement("a");
		downloadLink.download = "_MS_123.doc";
		downloadLink.innerHTML = "Download File";
		downloadLink.href = textToSaveAsURL;
		downloadLink.onclick = destroyClickedElement;
		downloadLink.style.display = "none";
		document.body.appendChild(downloadLink);
	 
		downloadLink.click();
	}
	
	function destroyClickedElement(event) {
		document.body.removeChild(event.target);
	}