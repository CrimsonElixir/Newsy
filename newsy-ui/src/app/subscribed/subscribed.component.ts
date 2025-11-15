import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ArrowRight, CheckCircle, LucideAngularModule} from 'lucide-angular';

@Component({
  selector: 'app-subscribed',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  templateUrl: './subscribed.component.html',
  styleUrls: ['./subscribed.component.css']
})
export class SubscribedComponent {
  CheckCircle = CheckCircle;
  ArrowRight = ArrowRight;

  goHome() {
    window.location.href = '/';
  }
}
