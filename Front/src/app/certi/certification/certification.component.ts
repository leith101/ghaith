import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-certification',
  templateUrl: './certification.component.html',
  styleUrls: ['./certification.component.css']
})
export class CertificationComponent {
  certification: any = {}; // Object to hold certification data
  apiUrl = 'http://localhost:8089/certifications/ajoutercerti'; // Replace with your actual API URL

  constructor(private http: HttpClient) {}

  addCertification() {
    this.http.post(this.apiUrl, this.certification)
      .subscribe((response: any) => {
        console.log('Certification added successfully:', response);
        // Optionally, reset the form or take any other action upon successful addition
      }, (error: any) => {
        console.error('Error adding certification:', error);
        // Handle error appropriately (e.g., show error message)
      });
  }
  
}
