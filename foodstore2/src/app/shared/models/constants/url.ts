const API_BASE_URL = 'http://localhost:8080/api/food';
export const ENDPOINTS = {
    GET_ALL_FOODS: `${API_BASE_URL}/all`,
    GET_FOOD_BY_ID: `${API_BASE_URL}/find/{id}`,
    ADD_FOOD: `${API_BASE_URL}/add`,
    UPDATE_FOOD: `${API_BASE_URL}/update`,
    DELETE_FOOD: `${API_BASE_URL}/delete/{id}`
  };