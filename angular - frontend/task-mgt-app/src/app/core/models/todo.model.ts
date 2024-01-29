import {
  ITodoPrior,
  ITodoType,
} from '../../shared/components/todo-card/todo-card.component';

export interface IResponse<T> {
  data: T;
  content: T;
  message?: string;
}

export interface ITodo {
  id?: number;
  title: string;
  description: string;
  dueDate?: string;
  priority: ITodoPrior;
  status: ITodoType;
  created_at?: string;
  updated_at?: string;
  deleted_at?: string;
}
