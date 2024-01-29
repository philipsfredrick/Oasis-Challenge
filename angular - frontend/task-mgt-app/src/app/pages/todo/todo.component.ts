import { Component, Input, OnInit } from '@angular/core';
import {
  ITodoPriority,
  ITodoStatus,
  TodoCardComponent,
} from '../../shared/components/todo-card/todo-card.component';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { provideNativeDateAdapter } from '@angular/material/core';
import { heroPlus, heroFunnel } from '@ng-icons/heroicons/outline';
import { TodoService } from '../../core/services/todo.service';
import { ITodo } from '../../core/models/todo.model';
import { SlidePanelComponent } from '../../shared/ui/slide-panel/slide-panel.component';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [
    TodoCardComponent,
    NgIconComponent,
    SlidePanelComponent,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatPaginatorModule,
    ReactiveFormsModule,
  ],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.scss',
  providers: [provideNativeDateAdapter()],
  viewProviders: [provideIcons({ heroPlus, heroFunnel })],
})
export class TodoComponent implements OnInit {
  todoForm!: FormGroup;
  todos: ITodo[] = [];
  todoStatus = ITodoStatus;
  todoPriority = ITodoPriority;
  isSlidePanelOpen = false;
  todoId: number | null = null;
  filterByStatus = '';
  filterByPriority = '';
  filterByDueDate = '';
  constructor(private todoService: TodoService, private fb: FormBuilder) {
    this.todoForm = this.fb.group({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      dueDate: new FormControl(null, [Validators.required]),
      status: new FormControl('PENDING', [Validators.required]),
      priority: new FormControl('CRITICAL', [Validators.required]),
    });
  }
  ngOnInit(): void {
    this.getAllTodos();
  }

  getAllTodos() {
    this.todoService
      .getAllTodo(
        this.filterByStatus,
        this.filterByPriority
        // this.filterByDueDate
      )
      .subscribe({
        next: (response) => {
          this.todos = response.content;
        },
      });
  }

  openSlidePanel() {
    this.isSlidePanelOpen = true;
  }

  onCloseSlidePanel() {
    this.isSlidePanelOpen = false;
  }

  onFilterByStatus(status: string) {
    this.filterByStatus = status;
    this.getAllTodos();
  }

  onFilterByPriority(priority: string) {
    this.filterByPriority = priority;
    this.getAllTodos();
  }

  onFilterByDueDate(dueDate: string) {
    this.filterByDueDate = dueDate;
    this.getAllTodos();
  }

  onSubmit() {
    if (this.todoForm.valid) {
      if (this.todoId) {
        this.todoService
          .updateTodo(this.todoId, this.todoForm.value)
          .subscribe({
            next: (response) => {
              this.getAllTodos();
              this.onCloseSlidePanel();
            },
          });
      } else {
        this.todoService.addTodo(this.todoForm.value).subscribe({
          next: (response) => {
            this.getAllTodos();
            this.onCloseSlidePanel();
          },
        });
      }
    } else {
      this.todoForm.markAllAsTouched();
    }
  }

  onLoadTodoForm(item: ITodo) {
    this.todoId = item.id!!;
    this.todoForm.patchValue({
      title: item.title,
      description: item.description,
      dueDate: item.dueDate,
      status: item.status,
      priority: item.priority,
    });
    this.openSlidePanel();
  }
}
