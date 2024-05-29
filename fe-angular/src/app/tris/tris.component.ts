import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

import { TutorialsService } from '../services/tutorials.service';
import { mergeScan } from 'rxjs';

@Component({
  selector: 'app-tris',
  standalone: true,
  imports: [RouterOutlet, JsonPipe, HttpClientModule,CommonModule,FormsModule],
  templateUrl: './tris.component.html',
  styleUrl: './tris.component.css'
})
export class TrisComponent {


  player1="";
  player2="";
  login_success = false;

  response: any;

  btn_reset = false;

  vincitori:string[] = []
  esito_partita = "" // punteggio p1 : punteggio p2
  punteggio_p1 : number = 0
  punteggio_p2 : number = 0
  
  array: string[][] = [
    ['', '', ''],
    ['', '', ''],
    ['', '', '']
  ];

  segno = ["X","O"]
  players : string[]= [];

  segnoValue = 0

  constructor(private tutorialsService: TutorialsService) {}

  return_body(posX:number,posY:number,segno:string,player:string){
    return {
      'posizioneX':posX,
      'posizioneY':posY,
      'segno':segno,
      'nomeGiocatore':player
    }
  }

  ngOnInit() {  
    // DA fare

  }
  click(posX: number, posY: number) {
    if (this.isDisabled(posX, posY)) {
      return;
    }

    this.tutorialsService.sendPos(this.return_body(posX, posY, this.segno[this.segnoValue], this.players[this.segnoValue])).subscribe((data: any) => {
      this.response = data.msg;
      if (data.msg) {
        //alert(data.msg);
        if (data.msg.includes('vincitore') || data.msg.includes('pareggio')) {
          this.btn_reset = true;
          this.disableAllButtons();
          if(data.msg.includes('vincitore')) this.vincitori.push(this.response)
          this.get_esito_partita()
        }
        
      }
    });

    this.array[posX][posY] = this.segno[this.segnoValue];
    this.setSegno();
  }
  setSegno() {
    this.segnoValue = this.segnoValue === 0 ? 1 : 0;
  }
  isDisabled(posX: number, posY: number) {
    return this.array[posX][posY] !== '';
  }

  reset() {
    this.array = [
      ['', '', ''],
      ['', '', ''],
      ['', '', '']
    ];
    this.segnoValue = 0;

    this.btn_reset = false;
  }

  disableAllButtons() {
    const buttons = document.querySelectorAll<HTMLButtonElement>('.board button');
    buttons.forEach((button) => {
      button.disabled = true;
    });
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.setPlayer(form.value.player1,form.value.player2)
      this.set_login_success(true)
      this.tutorialsService.start(this.player1,this.player2).subscribe(response=>{
        console.log("success")
      });
      this.players.push(this.player1)
      this.players.push(this.player2)
    }
  }

  setPlayer(ply1:string,ply2:string){
    this.player1 = ply1
    this.player2 = ply2
  }

  set_login_success(value:boolean){
      this.login_success = value
  }

  get_esito_partita(){
    this.punteggio_p1 = 0
    this.punteggio_p2 = 0

    this.vincitori.forEach((item)=>item.includes(this.segno[0]) ? this.punteggio_p1++ : this.punteggio_p2++
    
  )
  }


}
