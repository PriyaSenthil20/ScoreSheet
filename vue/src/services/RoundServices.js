import axios from 'axios';



const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API

});

export default {
  // Fetch all orders (admin view)
  createRound(round) {
    return http.post('/games/rounds',round);

  }
}