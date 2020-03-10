$(document).ready(
		function() {

			// SUBMIT FORM
			$("#LoginForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					qtdeValor : $("#login").val(),
					elegivel : $("#password").val()
				}

				 DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/controlecampanha",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(newSave) {
						console.log("success !!")
					}
				});

			}

		})
