let keys = document.querySelectorAll('[key]');

function playAudioClick(key) {
	let note = document.getElementById(key.dataset.key);
	note.currentTime = 0;
	note.play();
	key.classList.add('active');
	note.addEventListener('ended', () => {
		key.classList.remove('active');
	});
}

keys.forEach((key) => {
	key.addEventListener('click', () => {
		playAudioClick(key);
	});
});

document.addEventListener('keydown', (event) => {
	if (event.key === 'z') playAudioClick(document.querySelector('[c]'));
	if (event.key === 's') playAudioClick(document.querySelector('[df]'));
	if (event.key === 'x') playAudioClick(document.querySelector('[d]'));
	if (event.key === 'd') playAudioClick(document.querySelector('[ef]'));
	if (event.key === 'c') playAudioClick(document.querySelector('[e]'));
	if (event.key === 'v') playAudioClick(document.querySelector('[f]'));
	if (event.key === 'g') playAudioClick(document.querySelector('[gf]'));
	if (event.key === 'b') playAudioClick(document.querySelector('[g]'));
	if (event.key === 'h') playAudioClick(document.querySelector('[af]'));
	if (event.key === 'n') playAudioClick(document.querySelector('[a]'));
	if (event.key === 'j') playAudioClick(document.querySelector('[bf]'));
	if (event.key === 'm') playAudioClick(document.querySelector('[b]'));
	return;
});
