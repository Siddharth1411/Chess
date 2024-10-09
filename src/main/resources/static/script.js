const imageMap = {
    "white King": "./images/white-king1.svg",
    "white queen": "./images/white-queen1.svg",
    "white rook": "./images/white-rook1.svg",
    "white bishop": "./images/white-bishop1.svg",
    "white knight": "./images/white-knight1.svg",
    "white pawn": "./images/white-pawn1.svg",
    "black King": "./images/black-king1.svg",
    "black queen": "images/black-queen1.svg",
    "black rook": "images/black-rook1.svg",
    "black bishop": "images/black-bishop1.svg",
    "black knight": "images/black-knight1.svg",
    "black pawn": "images/black-pawn1.svg"

  };

//reset button
  const resetButton = document.getElementById('reset');
  if(resetButton) {
      resetButton.addEventListener('click', function() {
          fetch('/api/reset', {
              method: 'POST'
          })
          .then(response => {
              if (response.ok) {
                  console.log("Reset was successful");
                  window.location.reload();
              } else {
                  console.error("Reset failed");
              }
          })
          .catch(error => console.error("Error in reset request:", error));
      });
  }  
  
const grid = document.getElementById('grid');
let isMouseEventsDisabled = false;  //to disable hover when a cell is clicked

//currentRow, currentCol store the source cell before movement
let currentRow = -1;
let currentCol = -1;


for (let row = 0; row <= 7; row++) {
    for (let col = 0; col <= 7; col++) {
        const cell = document.createElement('div');
        cell.className = 'cell';
        cell.dataset.row = row;
        cell.dataset.col = col;
        cell.textContent = `${row}, ${col}`;  
        cell.addEventListener('mouseover', handleMouseOver);
        cell.addEventListener('mouseout', handleMouseOut);
        cell.addEventListener('click', Mark_and_Move);
        getPiece(row,col);
        grid.appendChild(cell);
        
    }
}

//this function marks the destination cells and on further click moves the piece to it
async function Mark_and_Move(event){
    isMouseEventsDisabled = !isMouseEventsDisabled;
    const cell = event.target;
    const row = cell.dataset.row;
    const col = cell.dataset.col;
    //if destination cells are already marked by one click => then this click moves
    //the piece from source to destination cell
    if(cell.style.backgroundColor == 'red' && currentRow != -1){
        sendMove(currentRow, currentCol, row, col);
        window.location.reload(); //reload page to reflect changed state
        //reset source cell
        currentRow = -1;
        currentRow = -1;
        return;
    }
    try {
        //this is executed when a cell clicked once => marks destination cells
        currentRow = row;
        currentCol = col;
        const response = await fetch(`/api/valid-moves?row=${row}&col=${col}`);
        const coordinates = await response.json();
        //list of cells to be marked(where the piece can move)
        coordinates.forEach(coordinate => {
            const targetCell = document.querySelector(`.cell[data-row='${coordinate.row}'][data-col='${coordinate.col}']`);
            targetCell.style.backgroundColor = 'red'; 
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

//this function calls the api to move the piece
function sendMove(startRow, startCol, endRow, endCol) {
    const requestData = {
        start: {
            row: startRow,
            col: startCol
        },
        end: {
            row: endRow,
            col: endCol
        }
    };

    fetch('/api/move-piece', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify(requestData) 
    })
    .then(response => {
        if (response.ok) {
            console.log("Move processed successfully");
        } else {
            console.error('Failed to process move');
        }
    })
    .catch(error => console.error('Error:', error));
}

//this function retrieves what piece is placed in each cell
function getPiece(row, col) {
    // Send row and col to the backend
    fetch(`/api/cell-data?row=${row}&col=${col}`)
        .then(response => response.json())
        .then(data => {
            const cell = document.querySelector(`.cell[data-row='${row}'][data-col='${col}']`);
            s = `${data.info}`;
            if(s != "empty cell"){
                const img = document.createElement('img'); 
                img.src = imageMap[s] || './images/default.png';                        
                img.style.width = '80%';                  
                cell.innerHTML = ''; //to clear the existing coordinates in each cell                     
                cell.appendChild(img); 
            }                   
            else cell.textContent = ""; //empty cell
        })
        .catch(error => console.error('Error fetching data:', error));
}

//marks the valid destination cells on hover
async function handleMouseOver(event) {
    if (isMouseEventsDisabled) return;
    const cell = event.target;
    const row = cell.dataset.row;
    const col = cell.dataset.col;

    try {
        const response = await fetch(`/api/valid-moves?row=${row}&col=${col}`);
        const coordinates = await response.json(); 
        coordinates.forEach(coordinate => {
            const targetCell = document.querySelector(`.cell[data-row='${coordinate.row}'][data-col='${coordinate.col}']`);
            targetCell.style.backgroundColor = 'red';
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

//unmarking destination cells when mouse moved out
function handleMouseOut(event) {
    if (isMouseEventsDisabled) return;
    const cell = event.target;
    const row = cell.dataset.row;
    const col = cell.dataset.col;
    const allCells = document.querySelectorAll('.cell');
    allCells.forEach(targetCell => {
        targetCell.style.backgroundColor = 'lightblue'; 
    });
}