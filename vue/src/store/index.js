import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import GameServices from '../services/GameServices';
import AuthService from '../services/AuthService';


export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      game_id:0,
      round_id:[]
      
    }, 
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_GAME_ID(state,game_id){
        state.game_id=game_id;
      },
      ADD_ROUND_ID(state,round_id){
        state.round_id.push(round_id);
      }
    },
      getters: {
        // Getter to retrieve the most recently added round_id
        latestRoundId(state) {
          return state.round_id[state.round_id.length - 1]; // Gets the last added round_id
        }
      }
    
  });
  return store;
}
