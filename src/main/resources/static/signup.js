document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.querySelector('section');
    loginForm.style.opacity = 0;

    setTimeout(() => {
        loginForm.style.transition = 'opacity 1s ease-in-out';
        loginForm.style.opacity = 1;
    }, 500);

    const loginButton = document.querySelector('button');
    loginButton.addEventListener('click', function (event) {
        const usernameInput = document.querySelector('input[name="felhasznalonev"]');
        const passwordInput = document.querySelector('input[name="jelszo"]');

        const isValid = usernameInput.checkValidity() && passwordInput.checkValidity();

        if (!isValid) {
            event.preventDefault();
            loginForm.classList.add('shake');

            setTimeout(() => {
                loginForm.classList.remove('shake');
            }, 1000);
        }
    });
});
