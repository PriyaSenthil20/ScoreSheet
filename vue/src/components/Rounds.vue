<template>
    <div>Create New Round</div>
    <button type="button" id="newRound" v-on:click="add()">+</button>
    <div class="roundComponent" v-if="this.addFlag === true">
    <form class="round-selection" v-on:submit.prevent="createRound()">    
        <div class="form-group">
            <label for="roundNumber">Round {{this.round.round_number}}</label>
            </div>
    
    <div class="form-group"><label for="roundName">Enter Round Name</label>
          <input
            type="text"
            id="roundName"
            v-model="round.round_name"
            maxlength="100"
            placeholder="OPTIONAL"
          /></div>

     <div class="form-group">
          <button type="submit">Continue</button>
        </div>
        <div class="form-group">
          <button type="button" id="cancel">Cancel</button>
        </div>
       
      </form>
    </div>
</template>
<script>
import RoundServices from '../services/RoundServices';
export default{
    data(){
        return{
            round:{
                round_id:0,
                game_id:0,
                round_number:0,
                round_name : null
            },
            addFlag:false,
        
            
        }
    },
    /**
     * 
     */
    methods: {
        add(){
            this.round.round_number+=1;
            this.addFlag=true;
        },
        createRound(){
          alert(this.$store.state.game_id);
          this.round.game_id=this.$store.state.game_id;
          RoundServices.createRound(this.round).then((response) => {
           const roundId = response.data.round_id;
           alert(roundId);
           this.$store.commit('ADD_ROUND_ID',this.roundId);
          })
          .catch(error => {
            console.error('Error creating round:', error);
          });
        }
    }
}
</script>