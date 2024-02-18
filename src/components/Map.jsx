import React from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { Icon } from "leaflet";

function Map() {
  return (
    <MapContainer center={["14.945923", "-16.80831"]} zoom={13}>
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      />

      <Marker position={["14.945923", "-16.80831"]}>
        <Popup>Pression : 0.8</Popup>
      </Marker>
      <Marker position={["14.945923", "-16.81831"]}>
        <Popup>Pression : 0.8001</Popup>
      </Marker>
      <Marker position={["14.945923", "-16.82831"]}>
        <Popup>Pression : 0.800</Popup>
      </Marker>
      <Marker position={["14.945923", "-16.83831"]}>
        <Popup>Pression : 0.8003</Popup>
      </Marker>
      <Marker position={["14.945923", "-16.84831"]}>
        <Popup>Pression : 0.8001</Popup>
      </Marker>

      {/* <Marker position={["20.45923", "-16.80831"]}>
        <Popup>A custom popup content goes here!</Popup>
      </Marker> */}
    </MapContainer>
  );
}

export default Map;
