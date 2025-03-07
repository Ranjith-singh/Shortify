import { useQuery } from "@tanstack/react-query";
import api from "../api/api";

export const useFetchShortUrls = (token, onError) => {
  const startDate = "2025-02-01";
  const endDate = "2026-02-01";

  return useQuery({
    queryKey: ["my-shortUrls", startDate, endDate], // Unique query key
    queryFn: async () => {
      const response = await api.get(
        `http://localhost:8080/api/urls/totalClicks?startDate=${startDate}&endDate=${endDate}`,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: "Bearer " + token,
          },
        }
      );
      return response.data; // Return only data
    },
    select: (data) => {
      return Object.keys(data).map((key) => ({
        clickDate: key,
        count: data[key],
      }));
    },
    onError,
    staleTime: 5000,
  });
};
