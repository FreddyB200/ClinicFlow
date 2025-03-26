/**
 * Main JavaScript file for the Medical Appointments application
 */

// When the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Enable all tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[title]'));
    var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Automatically hide alerts after 5 seconds
    setTimeout(function() {
        var alerts = document.querySelectorAll('.alert:not(.alert-permanent)');
        alerts.forEach(function(alert) {
            var bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        });
    }, 5000);
    
    // Add active class to current nav item
    var currentLocation = window.location.pathname;
    var navLinks = document.querySelectorAll('.navbar-nav .nav-link');
    navLinks.forEach(function(link) {
        if (link.getAttribute('href') === currentLocation) {
            link.classList.add('active');
        }
    });
    
    // Set up the current year in footer copyright text
    var currentYear = new Date().getFullYear();
    var copyrightElements = document.querySelectorAll('.copyright-year');
    copyrightElements.forEach(function(element) {
        element.textContent = currentYear;
    });
    
    // Add confirmation to dangerous actions
    var dangerButtons = document.querySelectorAll('.btn-danger:not([disabled])');
    dangerButtons.forEach(function(button) {
        button.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to perform this action?')) {
                e.preventDefault();
                return false;
            }
        });
    });
}); 