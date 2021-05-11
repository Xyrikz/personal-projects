const numberButtons = document.querySelectorAll('[number]');
const operationButtons = document.querySelectorAll('[operation]');
const equalsButton = document.querySelector('[equals]');
const deleteButton = document.querySelector('[del]');
const allClearButton = document.querySelector('[ac]');
const previousOperandTextElement = document.querySelector('[previous]');
const currentOperandTextElement = document.querySelector('[current]');
const brackets = document.querySelectorAll('[bracket]');
const ans = document.querySelector('[ans]');
const square = document.querySelector('[square]');
const root = document.querySelector('[root]');

class Calculator {
	constructor(previousOperandTextElement, currentOperandTextElement) {
		this.previousOperandTextElement = previousOperandTextElement;
		this.currentOperandTextElement = currentOperandTextElement;
		this.ans = null;
		this.clear();
	}

	clear() {
		this.currentOperand = '';
		this.previousOperand = '';
		this.operation = undefined;
	}
	appendNumber(number) {
		if (number === '.' && this.currentOperand.includes('.')) return;
		this.currentOperand =
			this.currentOperand.toString() + number.toString();
	}

	chooseOperation(operation) {
		if (this.currentOperand === '') return;
		if (this.previousOperand !== '') this.compute();
		this.operation = operation;
		this.previousOperand = `${this.currentOperand} ${this.operation}`;
		this.currentOperand = '';

		if (
			this.previousOperand.includes('^2') ||
			this.previousOperand.includes('root')
		) {
			this.compute();
		}
	}

	delete() {
		this.currentOperand = this.currentOperand.slice(0, -1);
	}

	compute() {
		let computation;
		let prev = parseFloat(this.previousOperand);
		let current = parseFloat(this.currentOperand);

		if (this.operation === '^2') {
			computation = prev ** 2;
			this.previousOperand = `${prev}² =`;
			this.currentOperand = computation;
			this.ans = computation;
		}
		if (this.operation === 'root') {
			computation = Math.sqrt(prev);
			this.previousOperand = `√${prev} =`;
			this.currentOperand = computation;
			this.ans = computation;
		}

		if (this.operation !== '^2' && this.operation !== 'root') {
			if (isNaN(prev) || isNaN(current)) return;

			switch (this.operation) {
				case '+':
					computation = prev + current;
					break;
				case '-':
					computation = prev - current;
					break;
				case '×':
					computation = prev * current;
					break;
				case '÷':
					computation = prev / current;
					break;
				default:
					return;
			}
			this.previousOperand = `${prev} ${this.operation} ${current} =`;
			this.currentOperand = computation;
			this.ans = computation;
		}
	}

	previousAns() {
		this.currentOperand = this.ans;
	}

	updateDisplay() {
		this.currentOperandTextElement.innerText = this.currentOperand;
		this.previousOperandTextElement.innerText = this.previousOperand;
	}
}

let calculator = new Calculator(
	previousOperandTextElement,
	currentOperandTextElement
);

allClearButton.addEventListener('click', () => {
	calculator.clear();
	calculator.updateDisplay();
});

deleteButton.addEventListener('click', () => {
	calculator.delete();
	calculator.updateDisplay();
});

equalsButton.addEventListener('click', () => {
	calculator.compute();
	calculator.updateDisplay();
});

ans.addEventListener('click', () => {
	calculator.previousAns();
	calculator.updateDisplay();
});

numberButtons.forEach((button) => {
	button.addEventListener('click', () => {
		calculator.appendNumber(button.innerText);
		calculator.updateDisplay();
	});
});

operationButtons.forEach((button) => {
	button.addEventListener('click', () => {
		calculator.chooseOperation(button.innerText);
		calculator.updateDisplay();
	});
});

square.addEventListener('click', () => {
	calculator.chooseOperation('^2');
	calculator.updateDisplay();
});

root.addEventListener('click', () => {
	calculator.chooseOperation('root');
	calculator.updateDisplay();
});
