<template>
    <div>Enter Your Game Name</div>
    <div class="gameComponent">
    <form class="game-selection" v-on:submit.prevent="selectGame()">    
    
  
    <div class="form-group"><label for="gameName">Enter the name of the Game</label>
          <input
            type="text"
            id="gameName"
            v-model="game.game_name"
            maxlength="100"
            placeholder="Example: Sequence "
          /></div>

     <div class="form-group">
          <button type="submit">Submit</button>
        </div>
        <div class="form-group">
          <button type="button" id="cancel">Cancel</button>
        </div>
        <div class="form-group">
          <button type="button" id="skip">Skip</button>
        </div>
      </form>
    </div>
</template>
<script>
import GameServices from '../services/GameServices';
export default{
    data(){
        return{
            game:{
                game_id:null,
                game_name : null,
                game_date : null
            }
            
        }
    },
    /**
     * 
     */
    methods: {
        selectGame(){
          this.game.game_date=new Date().toLocaleDateString();
          GameServices.createGame(this.game).then((response) => {
           const gameId = response.data.game_id;
           alert(gameId);
           this.$store.commit('SET_GAME_ID',gameId);
           alert(this.$store.state.game_id);
          })
          .catch(error => {
            console.error('Error creating game:', error);
          });
        }
    }
}
</script>