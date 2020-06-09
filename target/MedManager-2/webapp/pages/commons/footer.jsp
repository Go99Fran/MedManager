<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<body>

	<!-- FOOTER -->
	<footer class="page-footer cyan darken-3">
		<div class="container">
			<div class="row">
				<div class="col l6 s12">
					<h5 class="white-text">Med Manager</h5>
					<p class="grey-text text-lighten-4">Web para el seguimiento de
						tratamientos contra el cancer.</p>
				</div>
				<div class="col l4 offset-l2 s12">
					<h5 class="white-text">Links</h5>
					<ul>
						<li><a class="grey-text text-lighten-3"
							href="https://twitter.com/GustavoVillold4" target="_blank">Twitter/GustavoVillold4</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">© 2020 Copyright</div>
		</div>
	</footer>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			M.AutoInit();
			var options = {
				format : 'dd/mm/yyyy',
				yearRange : [ 1900, 2100 ],
				changeMonth : true,
				changeYear : true,
			};
			var elems = document.querySelectorAll('.datepicker');
			var instance = M.Datepicker.init(elems, options);

		});
	</script>
	
</body>
</html>