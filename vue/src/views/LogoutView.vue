<template>
  <div class="home">
  </div>
  <h1>You're Now Logged Out</h1>
</template>

<script>


export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/");
  }
};
</script>
