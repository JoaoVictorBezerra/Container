import type { ResponseContainer } from "@/Models/container.models";
import api from "./api.service";
import axios from "axios";
const url: String = import.meta.env.VITE_API_URL;

export function getContainers() {
  const response = fetch(`${url}/containers`)
  .then()
  .catch()
}
