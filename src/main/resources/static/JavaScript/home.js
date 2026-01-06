// Home Page JavaScript - Hamburger Menu

function toggleMenu() {
	const hamburger = document.querySelector('.hamburger');
	const mobileMenu = document.getElementById('mobileMenu');
	if (hamburger && mobileMenu) {
		hamburger.classList.toggle('active');
		mobileMenu.classList.toggle('active');
		// Toggle display
		if (mobileMenu.style.display === 'flex') {
			mobileMenu.style.display = 'none';
		} else {
			mobileMenu.style.display = 'flex';
		}
	}
}

// Close menu when clicking on a link
document.addEventListener('DOMContentLoaded', function() {
	const mobileMenuLinks = document.querySelectorAll('.mobile-menu a');
	mobileMenuLinks.forEach(link => {
		link.addEventListener('click', function() {
			const hamburger = document.querySelector('.hamburger');
			const mobileMenu = document.getElementById('mobileMenu');
			if (hamburger && mobileMenu) {
				hamburger.classList.remove('active');
				mobileMenu.classList.remove('active');
				mobileMenu.style.display = 'none';
			}
		});
	});
});
