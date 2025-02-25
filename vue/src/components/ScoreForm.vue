<template>
  <div id="scoreboard" class="nes-container is-rounded is-dark">
    <h2>Welcome!</h2>

    <div v-if="!tableGenerated" id="details">
      <label for="playerCount" class="block mb-2">Enter Number of Players:</label>
      <input v-model.number="playerCount" type="number" min="1" class="border p-2 mb-4 w-32" />
      <br />
      <label for="roundCount" class="block mb-2">Enter Number of Rounds:</label>
      <input v-model.number="roundCount" type="number" min="1" class="border p-2 mb-4 w-32" />
      <br />
      <button @click="generateTable" class="bg-blue-500 text-white px-4 py-2 rounded-md">
        Enter 
      </button>
    </div>

    <div v-if="tableGenerated">
    <!---  <div class="relative">
        <button 
          v-if="scrollIndex > 0" 
          @click="scrollLeft" 
          class="absolute top-1/2 left-0 transform -translate-y-1/2 bg-gray-500 text-white px-4 py-2 rounded-md">
          &lt;
        </button>

        <div class="overflow-hidden w-full">
          <div class="flex" :style="{ transform: `translateX(-${scrollIndex * 33.33}%)` }">
            <div 
              v-for="(player, index) in players" 
              :key="index" 
              class="border p-2 text-center w-1/3">
              <input v-model="players[index]" type="text" class="border p-1 w-full text-center" />
            </div>
          </div>
        </div>

        <button 
          v-if="scrollIndex < players.length / 3 - 1" 
          @click="scrollRight" 
          class="absolute top-1/2 right-0 transform -translate-y-1/2 bg-gray-500 text-white px-4 py-2 rounded-md">
          &gt;
        </button>
      </div>-->
      <!-- Highest Scorer Section -->
      <div v-if="highestScorer" class="mt-4 font-bold text-xl text-center text-green-600">
        Highest Scorer: {{ highestScorer.name }} with {{ highestScorer.score }} points
      </div>
      <!-- Lowest Scorer Section -->
      <div v-if="lowestScorer" class="mt-4 font-bold text-xl text-center text-red-600">
        Lowest Scorer: {{ lowestScorer.name }} with {{ lowestScorer.score }} points
      </div>

      <table class="border-collapse border border-gray-400 w-full mt-4">
        <thead>
          <tr>
            <th class="border border-gray-400 p-2">Player/Round</th>
            <th v-for="(player, index) in players" :key="index" class="border border-gray-400 p-2">
              <input v-model="players[index]" type="text" class="border p-1 w-full text-center" />
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(round, rowIndex) in rounds" :key="rowIndex">
            <td class="border border-gray-400 p-2 text-center">{{ round }}</td>
            <td v-for="(_, colIndex) in players" :key="colIndex" class="border border-gray-400 p-2">
              <input v-model="scores[rowIndex][colIndex]" type="number" class="border p-1 w-full text-center" @input="updateScores" />
            </td>
          </tr>
          <!-- Total Score Row -->
          <tr>
            <td class="border border-gray-400 p-2 font-bold text-center">Total</td>
            <td v-for="(total, index) in playerTotals" :key="'total-' + index" class="border border-gray-400 p-2 font-bold text-center">
              {{ total }}
            </td>
          </tr>
        </tbody>
      </table>
      <!-- Ranking Button -->
      <button @click="rankPlayers" >
        Rank Players
      </button>

      <!-- Ranking View -->
      <div v-if="rankingVisible" class="mt-4">
        <h3 class="text-xl font-bold text-center">Player Rankings</h3>
        <ol class="list-decimal pl-6">
          <li v-for="(player, index) in rankedPlayers" :key="index">
            {{ player.name }} - {{ player.totalScore }} points
          </li>
        </ol>
        <button @click="continueRanking" >
          Hide Ranking
        </button>
        <button @click="finish" >
          Finish
        </button>
      </div>
      <!-- Buttons -->
      <button @click="addRound" >
        Add Round
      </button>
      <button @click="addPlayer" >
        Add Player
      </button>
      <div>
  <label for="removePlayer">Remove Player</label>
  <select v-model="removeIndex" id="removePlayer">
    <option v-for="(player, index) in players" :key="index" :value="index">
      {{ player }}
    </option>
  </select>
  <button @click="removePlayer(removeIndex)">Remove</button>
</div>
      <button @click="resetForm" >
        Reset Scores
      </button>
      
      <button @click="toggleEditPlayerNames" >
        {{ isEditing ? 'Save' : 'Edit Player Names' }}
      </button>
      <button @click="finish" >
          Exit
        </button>
      <!-- Error Message for Invalid Player Name -->
      <div v-if="nameError" class="text-red-500 mt-2">Player names must contain at least 1 character!</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScoreForm',
  data() {
    return {
      playerCount: 3,
      roundCount: 3,
      players: [],
      rounds: [],
      scores: [],
      tableGenerated: false,
      isEditing: false,
      nameError: false,
      highestScorer: null,
      lowestScorer: null,
      removeIndex:0,
      rankingVisible: false, // Whether the ranking view is visible
      rankedPlayers: [], // List of ranked players
      scrollIndex: 0, // Track the current scroll position // Added for lowest scorer
    };
  },
  computed: {
    playerTotals() {
      return this.players.map((_, playerIndex) =>
        this.scores.reduce((sum, round) => sum + (parseFloat(round[playerIndex]) || 0), 0)
      );
    }
  },
  methods: {
    generateTable() {
      this.players = Array.from({ length: this.playerCount }, (_, i) => `Player ${i + 1}`);
      this.rounds = Array.from({ length: this.roundCount }, (_, i) => `Round ${i + 1}`);
      this.scores = Array.from({ length: this.roundCount }, () => Array(this.playerCount).fill(""));

      this.tableGenerated = true;
      this.nameError = false;  // Reset the error when generating the table
      this.updateScores();
    },
    addRound() {
      const newRoundNumber = this.rounds.length + 1;
      this.rounds.push(`Round ${newRoundNumber}`);
      this.scores.push(Array(this.playerCount).fill(""));
      this.updateScores();
    },
    addPlayer() {
      const newPlayerNumber = this.players.length + 1;
      const newPlayerName = `Player ${newPlayerNumber}`;

      // Ask for average score or cancel
      let avgScore = prompt(`Enter an average score for ${newPlayerName} (or leave blank for 0):`);
      avgScore = avgScore === null || avgScore === "" || isNaN(avgScore) ? 0 : parseFloat(avgScore);

      this.players.push(newPlayerName);

      // Add new column with average score for all rounds
      this.scores.forEach(row => row.push(avgScore));
      this.updateScores();
    },
    removePlayer(index) {
      this.players.splice(index, 1);
      this.scores.forEach(row => row.splice(index, 1));
      this.updateScores();
    },
    toggleEditPlayerNames() {
      if (this.isEditing) {
        if (this.players.some(player => player.trim().length === 0)) {
          this.nameError = true; // Show error message
          return;
        } else {
          this.nameError = false; // No error, proceed
        }
      }
      this.isEditing = !this.isEditing;
    },
    resetForm() {
  const confirmReset = window.confirm("Are you sure you want to reset the scores? This will remove all scores.");
  
  if (confirmReset) {
    // Reset only the scores, leaving players and rounds intact
    this.scores = this.scores.map(row => row.map(() => "")); // Reset all score values to empty strings
    this.updateScores(); // Update highest and lowest scorers after resetting the scores
  }
},
    updateScores() {
      this.updateHighestScorer();
      this.updateLowestScorer(); // Call update for lowest scorer
    },
    updateHighestScorer() {
      const totals = this.playerTotals;
      const maxScore = Math.max(...totals);
      const highestIndex = totals.indexOf(maxScore);

      if (totals.length > 0) {
        this.highestScorer = {
          name: this.players[highestIndex],
          score: maxScore
        };
      }
    },
    updateLowestScorer() {
      const totals = this.playerTotals;
      const minScore = Math.min(...totals);
      const lowestIndex = totals.indexOf(minScore);

      if (totals.length > 0) {
        this.lowestScorer = {
          name: this.players[lowestIndex],
          score: minScore
        };
      }
    }, rankPlayers() {
      // Calculate the total score and rank players
      const playersWithScores = this.players.map((player, index) => ({
        name: player,
        totalScore: this.playerTotals[index]
      }));

      // Sort players based on total score (highest to lowest)
      this.rankedPlayers = playersWithScores.sort((a, b) => b.totalScore - a.totalScore);

      // Show the ranking view
      this.rankingVisible = true;
    },
    continueRanking() {
      this.rankingVisible = false; // Hide the ranking view to return to ScoreForm
    },
    finish() {
      const confirmReset = window.confirm("Are you sure you exit this scoresheet? You can not go back!");
  
      if (confirmReset) {
      this.rankingVisible = false;
      
      this.playerCount = 3;
        this.roundCount = 3;
        this.players = [];
        this.rounds = [];
        this.scores = [];
        this.tableGenerated = false;
        this.isEditing = false;
        this.nameError = false;
        this.highestScorer = null;
        this.lowestScorer = null;
    }
  },scrollLeft() {
      if (this.scrollIndex > 0) {
        this.scrollIndex -= 1;
      }
    },
    scrollRight() {
      if (this.scrollIndex < Math.floor(this.players.length / 3) - 1) {
        this.scrollIndex += 1;
      }
    },
  }
};
</script>

<style scoped>
/* Add styles for the score form */.#scoreboard {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #1a1a1a;
  color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  text-align: center;
}

/* Headings */
h2 {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #ffcc00;
}

/* Input Fields */
input {
  background-color: #333;
  color: white;
  border: 2px solid #ffcc00;
  padding: 6px;
  border-radius: 5px;
  text-align: center;
}

input:focus {
  outline: none;
  border-color: #ff6600;
}

/* Buttons */
button {
  background-color: #ff6600;
  color: white;
  border: none;
  padding: 8px 16px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #ffcc00;
  color: black;
}

button:active {
  transform: scale(0.95);
}

/* Table Styling */
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 2px solid #ffcc00;
  padding: 10px;
  text-align: center;
}

th {
  background-color: #ff6600;
  color: white;
}

td input {
  width: 80%;
}

/* Player List */
#removePlayer {
  background-color: #222;
  color: white;
  border: 2px solid #ffcc00;
  padding: 6px;
  border-radius: 5px;
  text-align: center;
  margin-left: 10px;
}

/* Player Rankings */
ol {
  text-align: left;
  background-color: #222;
  padding: 10px;
  border-radius: 5px;
}

li {
  font-size: 16px;
  font-weight: bold;
  color: #ffcc00;
  padding: 5px;
}

/* Error Messages */
.text-red-500 {
  color: red;
  font-weight: bold;
}

/* Utility Classes */
.mt-4 {
  margin-top: 20px;
}

.p-2 {
  padding: 10px;
}

.rounded-md {
  border-radius: 8px;
}
</style>
