const display = document.querySelector('[display]');
const startButton = document.querySelector('[start]');
const stopButton = document.querySelector('[stop]');
const resetButton = document.querySelector('[reset]');
reset();

function addTime() {
	time++;
	b = display.innerHTML = `${(time / 100).toFixed(2)} s`;
}

function reset() {
	running = false;
	time = 0;
	display.innerHTML = `${time} s`;
}

startButton.addEventListener('click', () => {
	if (!running) {
		duration = setInterval(addTime, 10);
	}
	running = true;
});

stopButton.addEventListener('click', () => {
	clearInterval(duration);
	running = false;
});

resetButton.addEventListener('click', () => {
	clearInterval(duration);
	reset();
});
