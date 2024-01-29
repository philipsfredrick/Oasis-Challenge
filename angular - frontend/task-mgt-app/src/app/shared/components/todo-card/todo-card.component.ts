import { Component, Input } from '@angular/core';
import { ITodo } from '../../../core/models/todo.model';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import {
  heroTrash,
  heroPencil,
  heroCalendar,
} from '@ng-icons/heroicons/outline';

export type ITodoType = 'ALL' | 'PENDING' | 'IN_PROGRESS' | 'COMPLETED';
export const ITodoStatus = ['PENDING', 'IN_PROGRESS', 'COMPLETED'];
export type ITodoPrior = 'CRITICAL' | 'HIGH' | 'MEDIUM' | 'LOW';
export const ITodoPriority = ['CRITICAL', 'HIGH', 'MEDIUM', 'LOW'];

@Component({
  selector: 'app-todo-card',
  standalone: true,
  imports: [NgIconComponent],
  templateUrl: './todo-card.component.html',
  styleUrl: './todo-card.component.scss',
  viewProviders: [provideIcons({ heroTrash, heroPencil, heroCalendar })],
})
export class TodoCardComponent {
  @Input() type: ITodoType = 'PENDING';
  @Input() todo!: ITodo;
  @Input() priority: ITodoPrior = 'CRITICAL';
}
