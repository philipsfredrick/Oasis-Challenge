import { Component, Input, OnInit } from '@angular/core';
import { TokenService } from '../../../core/services/token.service';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { heroUser } from '@ng-icons/heroicons/outline';
import { SlidePanelComponent } from '../../ui/slide-panel/slide-panel.component';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { IUser } from '../../../core/models/auth.model';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    NgIconComponent,
    SlidePanelComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  viewProviders: [provideIcons({ heroUser })],
})
export class HeaderComponent implements OnInit {
  @Input() user!: IUser;
  userForm!: FormGroup;
  userId: number | null = null;
  isSlidePanelOpen = false;
  isAuthenticated$;
  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    this.isAuthenticated$ = this.tokenService.isAuthentication;
    this.userForm = this.fb.group({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  getCurrentUser() {
    this.authService.getUser().subscribe({
      next: (response) => {
        this.user = response.content;
      },
    });
  }
  onLogout() {
    this.authService.onLogout();
  }

  openSlidePanel() {
    this.isSlidePanelOpen = true;
  }

  onCloseSlidePanel() {
    this.isSlidePanelOpen = false;
  }

  onSubmit() {
    if (this.userForm.valid) {
      if (this.userId) {
        this.authService.updateUser(this.userForm.value).subscribe({
          next: (response) => {
            this.onCloseSlidePanel();
          },
        });
      } else {
        this.userForm.markAllAsTouched();
      }
    }
  }

  onLoadUserForm(item: IUser) {
    this.userId = item.id!!;
    this.userForm.patchValue({
      name: item.name,
      email: item.email,
    });
    this.openSlidePanel();
  }
}
