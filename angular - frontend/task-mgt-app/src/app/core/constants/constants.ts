export const constants = {
  CURRENT_TOKEN: 'CURRENT_TOKEN',
};

const apiurl = 'http://localhost:9010/api/v1';

export const apiEndpoint = {
  AuthEndpoint: {
    register: `${apiurl}/auth/register`,
    login: `${apiurl}/auth/login`,
    logout: `${apiurl}/auth/logout`,
    loggedUser: `${apiurl}/auth/user`,
  },
  TodoEndpoint: {
    getAllTodo: `${apiurl}/tasks`,
    addTodo: `${apiurl}/tasks`,
    updateTodo: `${apiurl}/tasks`,
    getAllTodoStatus: `${apiurl}/tasks/status`,
    getAllTodoPriority: `${apiurl}/tasks/priority`,
    getAllTodoDueDate: `${apiurl}/tasks/due_date`,
  },
};
