import { Injectable } from '@angular/core';
import { IResponse, ITodo } from '../models/todo.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { apiEndpoint } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  constructor(private http: HttpClient) {}

  getAllTodo(
    status: string,
    priority: string
    // dueDate: string
  ): Observable<IResponse<ITodo[]>> {
    let endpoint = '';

    if (status !== '') {
      endpoint = `${apiEndpoint.TodoEndpoint.getAllTodoStatus}?status=${status}`;
    } else if (priority !== '') {
      endpoint = `${apiEndpoint.TodoEndpoint.getAllTodoPriority}?priority=${priority}`;
      // } else if (dueDate !== '') {
      //   endpoint = `${apiEndpoint.TodoEndpoint.getAllTodoDueDate}?dueDate=${dueDate}`;
    } else {
      // If none of the parameters is provided, fetch all Todos
      endpoint = apiEndpoint.TodoEndpoint.getAllTodo;
    }

    // Make the API request
    return this.http.get<IResponse<ITodo[]>>(endpoint);
    // let queryString = '';
    // if (status !== '') {
    //   queryString = `status=${status}`;
    // } else if (priority !== '') {
    //   queryString = `priority=${priority}`;
    // } else if (dueDate !== '') {
    //   queryString = `dueDate=${dueDate}`;
    // }
    // if (queryString !== '') {
    //   // queryString = `status=${status}`;
    //   return this.http.get<IResponse<ITodo[]>>(
    //     `${apiEndpoint.TodoEndpoint.getAllTodoStatus}?${queryString}`
    //   );
    // }
    // if (priority !== '') {
    //   queryString = `priority=${priority}`;
    //   return this.http.get<IResponse<ITodo[]>>(
    //     `${apiEndpoint.TodoEndpoint.getAllTodoPriority}?${queryString}`
    //   );
    // }
    // if (dueDate !== '') {
    //   queryString = `priority=${priority}`;
    //   return this.http.get<IResponse<ITodo[]>>(
    //     `${apiEndpoint.TodoEndpoint.getAllTodoDueDate}?${queryString}`
    //   );
    // }
    // return this.http.get<IResponse<ITodo[]>>(
    //   `${apiEndpoint.TodoEndpoint.getAllTodo}`
    // );
  }

  // private buildQueryString(
  //   status: string,
  //   priority: string,
  //   dueDate: string
  // ): string {
  //   const queryParams = [];

  //   if (status !== '') {
  //     queryParams.push(`status=${status}`);
  //   }
  //   if (priority !== '') {
  //     queryParams.push(`priority=${priority}`);
  //   }
  //   if (dueDate !== '') {
  //     queryParams.push(`dueDate=${dueDate}`);
  //   }

  //   return queryParams.join('&');
  // }

  getAllTodoPriority(priority: string): Observable<IResponse<ITodo[]>> {
    let queryString = '';
    if (priority !== '') {
      queryString = `priority=${priority}`;
    }
    return this.http.get<IResponse<ITodo[]>>(
      // modify endpoint for priority
      `${apiEndpoint.TodoEndpoint.getAllTodoPriority}?${queryString}`
    );
  }

  getAllTodoDueDate(dueDate: string): Observable<IResponse<ITodo[]>> {
    let queryString = '';
    if (dueDate !== '') {
      queryString = `dueDate=${dueDate}`;
    }
    return this.http.get<IResponse<ITodo[]>>(
      // modify endpoint for due date
      `${apiEndpoint.TodoEndpoint.getAllTodoDueDate}?${queryString}`
    );
  }

  addTodo(data: ITodo): Observable<IResponse<ITodo>> {
    return this.http.post<IResponse<ITodo>>(
      `${apiEndpoint.TodoEndpoint.addTodo}`,
      data
    );
  }

  updateTodo(id: number, data: ITodo): Observable<IResponse<ITodo>> {
    return this.http.patch<IResponse<ITodo>>(
      `${apiEndpoint.TodoEndpoint.updateTodo}/${id}`,
      data
    );
  }
}
