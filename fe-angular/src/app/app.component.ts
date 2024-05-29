import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TrisComponent   } from './tris/tris.component';

import { TutorialsService } from './services/tutorials.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, JsonPipe, HttpClientModule,TrisComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'fe-angular';

  tutorials: any;

  constructor(private tutorialsService: TutorialsService) {}

  ngOnInit() {
    //da fare
  }

}
