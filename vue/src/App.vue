
<template>
  <div id="app">
    <header>
      <h1>Score Sheet</h1>
    </header>
    <ScoreForm @submitScore="addScore" />
    <ScoreList :scores="scores" />
  </div>
</template>

<script>
import ScoreForm from './components/ScoreForm.vue';
import ScoreList from './components/ScoreList.vue';

export default {
  name: 'App',
  components: {
    ScoreForm,
    ScoreList,
  },
  data() {
    return {
      scores: [],
    };
  },
  methods: {
    addScore(newScore) {
      this.scores.push(newScore);
    },
  },
};
</script>

<style>
/* You can style the App here */
</style>