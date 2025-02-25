<template>
    <section class="score-list">
      <h2>Scoreboard</h2>
      <ul>
        <li v-for="(score, index) in scores" :key="index">
          {{ score.player }}: {{ score.score }}
        </li>
      </ul>
    </section>
  </template>
  
  <script>
  export default {
    name: 'ScoreList',
    props: {
      scores: Array,
    },
  };
  </script>
  
  <style scoped>
  /* Add styles for the score list */
  .score-list {
    max-width: 500px;
    margin: 0 auto;
  }
  
  .score-list ul {
    list-style: none;
    padding: 0;
  }
  
  .score-list li {
    background-color: #fff;
    padding: 10px;
    margin-bottom: 5px;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  </style>