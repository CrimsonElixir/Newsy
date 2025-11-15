import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LucideAngularModule, XCircle, ArrowLeft } from 'lucide-angular';

@Component({
  selector: 'app-unsubscribed',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './unsubscribed.component.html',
  styleUrls: ['./unsubscribed.component.css']
})
export class UnsubscribedComponent {
  XCircle = XCircle;
  ArrowLeft = ArrowLeft;

  goHome() {
    window.location.href = '/';
  }
}
