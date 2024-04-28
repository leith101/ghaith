import { Component, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ScheduleService } from '../service/schedule.service';
import internal from 'stream';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrl: './schedule.component.css',
  providers: [provideNativeDateAdapter()],
  

})
export class ScheduleComponent {
  @ViewChild('map', { static: true }) map: any;


  async ngOnInit() {
    // Fetch existing stations from the database
    const stations = await this.getScheduleStationsFromDatabase();
  
    // Add markers for each station
  // Add markers for each station
stations.forEach((station: string) => {
  // Debugging: Log station data
  console.log('Station:', station);

  // Split the station string into latitude and longitude
  const [latStr, lngStr] = station.split(',');
  
  // Ensure lat and lng are numbers
  const lat = parseFloat(latStr);
  const lng = parseFloat(lngStr);

  // Check if lat and lng are valid numbers
  if (!isNaN(lat) && !isNaN(lng)) {
    const marker = new google.maps.Marker({
      position: { lat, lng },
      map: this.map
    });
  } else {
    console.error('Invalid station data:', station);
  }
});

  }
  
  
  async getScheduleStationsFromDatabase() {
    try {
      // Make an HTTP GET request to fetch stations from the database
      const response = await this.http.get<any>("http://localhost:8089/schedules/getschedulesstations").toPromise();
      return response; // Assuming response contains stations data
    } catch (error) {
      console.error("Error fetching stations:", error);
      return []; // Return an empty array in case of error
    }
  }
  

  area: string = "";
  date: string = "";
  station: string = ""; // Initialize station with default values
  availability: string = "";
  price: number = 0;
  selected!: Date | null;

  constructor(private schedule: ScheduleService, private http: HttpClient) {}

  handleClick(event: any) {
    const lat = event.latLng.lat();
    const lng = event.latLng.lng();
    console.log(`Clicked on: ${lat}, ${lng}`);
    this.station = `${lat},${lng}`; // Update station with lat,lng format
  }
  
  losave() {
    // Define an interface for the event data (optional)
    interface EventData {
      area: string;
      date: string; // Consider using a Date object for better date handling
      price: number;
      availability: string;
      station: string;
    }
  
    // Prepare event data
    const eventData: EventData = {
      area: this.area,
      date: this.selected ? this.selected.toISOString() : '', // Use optional chaining
      price: this.price,
      availability: this.availability,
      station: this.station,
    };
  
    // Set content type header (optional)
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    // Make the HTTP request
    this.http.post<any>("http://localhost:8089/schedules/ajouterschedule", eventData, { headers })
      .subscribe(
        (response) => {
          console.log(response); // Log the response data (might contain additional info)
          alert("Event created successfully!");
        },
        (error) => {
          console.error("Error creating event:", error);
          // Handle specific errors based on response status code or error message
          alert("Event creation failed. Please try again.");
        }
      );
  }
  
  
  }



