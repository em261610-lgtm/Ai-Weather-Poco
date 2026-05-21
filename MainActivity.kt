package com.example.aiweatherhyperos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                GeminiWeatherScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeminiWeatherScreen() {
    var searchQuery by remember { mutableStateOf("") }
    
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF111111), Color(0xFF1A1A2E))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "أهلاً بك يا صاحبي ✨",
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "الذكاء الاصطناعي يتوقع: طقس اليوم مشمس ولطيف",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF252538)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "القاهرة، مصر 🇪🇬", color = Color.White, fontSize = 20.sp)
                            Text(text = "32°م", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "مؤشر الأشعة فوق البنفسجية (UV):", color = Color.LightGray)
                            Text(text = "7 (مرتفع جداً) ⚠️", color = Color(0xFFFF5722), fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "شدة الـ UV على مدار اليوم ☀️:", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(12.dp))
                
                val uvHourlyList = listOf(
                    UvForecast("09:00 ص", "3", "متوسط"),
                    UvForecast("12:00 م", "8", "شديد جداً"),
                    UvForecast("03:00 م", "6", "مرتفع"),
                    UvForecast("06:00 م", "1", "ضعيف")
                )
                
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(uvHourlyList) { item ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E2E)),
                            modifier = Modifier.width(100.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = item.time, color = Color.Gray, fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = item.value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = item.intensity, color = Color(0xFF00BCD4), fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(text = "اسأل الذكاء الاصطناعي عن طقس أي مدينة... 🤖", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF8A2BE2),
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color(0xFF1E1E2E),
                    unfocusedContainerColor = Color(0xFF1E1E2E),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        }
    }
}

data class UvForecast(val time: String, val value: String, val intensity: String)
